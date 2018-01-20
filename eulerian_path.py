s_to_d = {}
d_to_s = {}
S = set()
D = set()


def FindStartNodeDict(graph):
    starts = graph.keys()
    for start in starts:
        out_degree = len(graph[start])
        # in_degree = sum([1 for x,y in graph.items() if start in y])
        in_degree = 0
        for x, y in graph.items():
            for i in y:
                i = i.strip(" ")
                i = i.strip("\n")
                if start.strip(" ") == i:
                    in_degree = in_degree + 1
        if out_degree > in_degree:
            start_node = start
            return start_node.strip(" ")


def read_input():
    final_result = []
    count = 0
    with open("input1.txt", "r") as fp:
        for line in fp.readlines():
            nodes = line.split("->")
            target_list = nodes[1].split(",")
            s_to_d[nodes[0]] = target_list
            for i in target_list:
                i = i.strip("\n")
                i = i.strip(" ")
                D.add(i)
                if i in d_to_s.keys():
                    d_to_s[i].append(nodes[0].strip(" "))
                else:
                    d_to_s[i] = list(nodes[0].strip(" "))
                count += 1
            S.add(nodes[0].strip(" "))
    # sink = set(S)^set(D)
    startNode = FindStartNodeDict(d_to_s)
    final_result.append(startNode)
    result = eulerian_walk(startNode, final_result, count)
    final = "->".join([i for i in reversed(result)])
    print(final)


visited = {}


def eulerian_walk(n, final_result, count):
    next_nodes = d_to_s[n]
    for j in range(len(next_nodes)):
        if next_nodes is not None:
            i = next_nodes[j]
            if (n, i) in visited.items():
                continue
            visited[n] = i
            final_result.append(i)
            for k in d_to_s[n]:
                if i == k:
                    list = d_to_s[n]
                    list.remove(k)
                    d_to_s[n] = list
            # next_nodes.remove(i)
            final_result = eulerian_walk(i, final_result, count)
            if len(final_result) != count + 1:
                final_result = final_result[:-1]
                if n in d_to_s.keys():
                    list = d_to_s[n]
                    list.insert(j, i)
                    d_to_s[n] = list
                del visited[n]
                j += 1
    return final_result


read_input()
