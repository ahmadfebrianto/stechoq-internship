import socket
from time import sleep
import json
import random
from pprint import pprint


def start_udp_broadcast(host, port):
    while True:
        msg = json.dumps([
            {"data_1": random.randint(0, 100)},
            {"data_2": random.randint(0, 100)}
        ])

        sock = socket.socket(
            socket.AF_INET, socket.SOCK_DGRAM, socket.IPPROTO_UDP)
        sock.setsockopt(socket.SOL_SOCKET, socket.SO_BROADCAST, 1)
        sock.bind((host, 0))
        sock.sendto(msg.encode(), (host, port))
        sock.close()
        pprint(msg.encode())
        sleep(3)


def main():
    HOST = "127.0.0.1"
    UDP_BROADCAST_PORT = 2020

    print(f"Broadcast message sent to port {UDP_BROADCAST_PORT}")
    start_udp_broadcast(HOST, UDP_BROADCAST_PORT)


if __name__ == '__main__':
    main()
