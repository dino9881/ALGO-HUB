import sys
import queue
N = int(sys.stdin.readline())
e = int(sys.stdin.readline())
q = queue.Queue()
visit = [0 for _ in range(N + 1)]
graph = [[] for _ in range(N + 1)]
for _ in range(e):
	A, B = map(int, sys.stdin.readline().split())
	graph[A].append(B)
	graph[B].append(A)
q.put(1)
while q.empty() is not True:
	node = q.get()
	visit[node] = 1
	for i in range(len(graph[node])):
		if visit[graph[node][i]] == 0:
			q.put(graph[node][i])
print(visit.count(1) -1)