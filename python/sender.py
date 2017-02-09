#
# UDP Sender
#

import socket
import sys

class UdpSender:

    def __init__(self, host, port):

        # creating a UDP socket
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

        self.server_address = ('localhost', 10000)

    def send(self, message):

        try:

            # send data
            print >>sys.stderr, 'sending "%s"' % message
            sent = self.sock.sendto(message, self.server_address)

            # Receive response
            print >>sys.stderr, 'waiting to receive'
            data, server = self.sock.recvfrom(4096)
            print >>sys.stderr, 'received "%s"' % data

        finally:
            print >>sys.stderr, 'closing socket'
            self.sock.close()

if __name__ == "__main__":

    # creating udp sender
    sender = UdpSender('localhost', 10000)

    message = 'This is the message.  It will be repeated.'
    sender.send(message)

