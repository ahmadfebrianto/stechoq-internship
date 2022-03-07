import asyncio
import socket
import http.server as server
import threading
import json
import random
import asyncio
from amqtt.client import MQTTClient, ConnectException
from http.client import HTTPConnection
from pprint import pprint


storage_1 = {}
storage_2 = {}
HOST = "127.0.0.1"
UDP_BROADCAST_PORT = 2020
HTTP_LISTEN_PORT = 3030
MQTT_BROKER_PORT = 4040


# class Client:

class UDP:
    def __init__(self):
        self.client = socket.socket(
            socket.AF_INET, socket.SOCK_DGRAM, socket.IPPROTO_UDP)
        self.client.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEPORT, 1)
        self.client.bind((HOST, UDP_BROADCAST_PORT))

    def _start(self):
        global storage_1, storage_2
        while True:
            data, addr = self.client.recvfrom(1024)
            if data:
                storage_1, storage_2 = json.loads(data.decode())
                pprint(storage_1)
                pprint(storage_2)

                http = HTTP()
                http.start()

                mqtt = MQTT()
                mqtt.start()

    def start(self):
        threading.Thread(target=self._start).start()

class HTTP:

    headers = {
        'Content-type': 'application/json',
        'Accept': 'application/json'
    }

    def __init__(self) -> None:
        self.client = HTTPConnection(HOST, HTTP_LISTEN_PORT)

    def post(self):
        # while True:
        try:
            self.client.request(
                'POST', '', json.dumps(storage_1), self.headers)
            response = self.client.getresponse()
            print(response.read())
        except Exception as e:
            print(e)

    def start(self):
        threading.Thread(target=self.post).start()

class MQTT:

    def __init__(self):
        self.loop = self.create_background_loop()

    def create_background_loop(self):
        loop = asyncio.new_event_loop()
        threading.Thread(target=loop.run_forever).start()
        return loop

    async def publish(self):
        C = MQTTClient()
        try:
            # while True:
            await C.connect(f'mqtt://{HOST}:{MQTT_BROKER_PORT}/')
            await C.publish('STECHOQ', json.dumps(storage_2).encode())
            await C.disconnect()

        except ConnectException as ce:
            pass

    def submit_task(self, task):
        return asyncio.run_coroutine_threadsafe(task, self.loop)

    def start(self):
        self.submit_task(self.publish())


def main():

    udp_client = UDP()
    udp_client.start()

    # htpp_client = HTTP()
    # htpp_client.start()
    
    # mqtt_client = MQTT()
    # mqtt_client.start()


if __name__ == '__main__':
    main()
