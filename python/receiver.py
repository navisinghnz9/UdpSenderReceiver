#
# UDP Receiver
#

import socket
import sys

class UdpReceiver:

    def __init__(self, host, port):

        # creating a UDP socket
        self.sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

        # binding the socket to the port
        server_address = (host, port)
        print >>sys.stderr, 'starting up on %s port %s' % server_address
        self.sock.bind(server_address)

    def listen(self):

        while True:
            print >>sys.stderr, '\nWaiting to receive message'
            data, address = self.sock.recvfrom(4096)
    
            print >>sys.stderr, 'received %s bytes from %s' % (len(data), address)
            print >>sys.stderr, data
    
            if data:
                sent = self.sock.sendto(data, address)
                print >>sys.stderr, 'sent %s bytes back to %s' % (sent, address)

if __name__ == "__main__":

    # creating udp receiver
    receiver = UdpReceiver('localhost', 10000)

    # listen for incoming messages
    receiver.listen()
