import asyncio
import http.server as server
import json
import websockets
import threading
from amqtt.broker import Broker


storage = {}
HOST = "127.0.0.1"
UDP_BROADCAST_PORT = 2020
HTTP_LISTEN_PORT = 3030
MQTT_BROKER_PORT = 4040
WEBSOCKET_PORT = 5050


class HTTP:
    class HTTPRequestHandler(server.BaseHTTPRequestHandler):
        def do_GET(self):
            self.send_response(200)
            self.send_header("Content-type", "application/json")
            self.end_headers()
            self.wfile.write(json.dumps(storage).encode())

        def do_POST(self):
            content_length = int(self.headers["Content-Length"])
            body = self.rfile.read(content_length)
            data = json.loads(body.decode())
            storage.update(data)
            self.send_response(200)
            self.end_headers()
            self.wfile.write(json.dumps(storage).encode())

    def _start(self):
        httpd = server.HTTPServer((HOST, HTTP_LISTEN_PORT), self.HTTPRequestHandler)
        httpd.serve_forever()

    def start(self):
        threading.Thread(target=self._start).start()


class MQTTBroker:
    def __init__(self):
        self.config = {
            "listeners": {"default": {"type": "tcp", "bind": "127.0.0.1:4040"}},
            "sys_interval": 10,
            "auth": {"allow-anonymous": True, "plugins": ["auth_anonymous"]},
            "topic-check": {
                "enabled": True,
                "plugins": ["topic_acl"],
                "acl": {
                    "anonymous": ["#"],
                },
            },
        }

    async def create_broker(self):
        broker = Broker(config=self.config)
        await broker.start()

    # async def start(self):
        # asyncio.get_event_loop().run_until_complete(self.create_broker())
        # asyncio.get_event_loop().run_forever()


class WebSocket:
    async def server(websocket, path):
        try:
            async for message in websocket:
                await websocket.send(f"{storage}")
        except Exception:
            pass

    async def start(self):
        start_server = await websockets.serve(server, "localhost", WEBSOCKET_PORT)
        # asyncio.get_event_loop().run_until_complete(start_server)
        # asyncio.get_event_loop().run_forever()


def main():

    http = HTTP()
    http.start()

    mqtt = MQTTBroker()
    # mqtt.start()

    websocket = WebSocket()
    # websocket.start()

    tasks = [
        mqtt.create_broker(),
        websocket.start(),
    ]
    asyncio.get_event_loop().run_until_complete(asyncio.gather(*tasks))
    asyncio.get_event_loop().run_forever()



if __name__ == "__main__":
    main()
