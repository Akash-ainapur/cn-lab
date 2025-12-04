# Experiment 1: Network Commands

## Objective
Learn to use commands like ping, tcpdump, netstat, ifconfig, nslookup and traceroute. Capture ping and traceroute PDUs using a network protocol analyzer and examine.

## Commands Overview

### 1. ping
Tests connectivity to a host and measures round-trip time.
```bash
ping -t <hostname>    # Continuous ping (Windows)
ping -c 4 <hostname>  # Send 4 packets (Linux)
```

### 2. tcpdump
Captures and analyzes network packets.
```bash
tcpdump -i eth0           # Capture on interface eth0
tcpdump -i eth0 -w file   # Write to file
tcpdump -r file           # Read from file
```

### 3. netstat
Displays network connections, routing tables, and interface statistics.
```bash
netstat -a    # Show all connections
netstat -r    # Show routing table
netstat -s    # Show statistics
```

### 4. ifconfig (Linux) / ipconfig (Windows)
Displays or configures network interface parameters.
```bash
ifconfig           # Show all interfaces (Linux)
ipconfig /all      # Show all interfaces (Windows)
```

### 5. nslookup
Queries DNS to obtain domain name or IP address mapping.
```bash
nslookup google.com
nslookup 8.8.8.8
```

### 6. traceroute (Linux) / tracert (Windows)
Traces the route packets take to a network host.
```bash
traceroute google.com   # Linux
tracert google.com      # Windows
```

## Lab Exercise
1. Use `ping` to test connectivity to a website
2. Use `traceroute` to see the path to the destination
3. Use `netstat` to view active connections
4. Use `nslookup` to resolve domain names
5. Capture packets using Wireshark or tcpdump during ping/traceroute
