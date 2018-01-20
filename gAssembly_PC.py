def get_edge(n, l):
    edge = [n[0:l - 1], n[1:l]]
    return edge


def getAssembly():
    with open('input.txt') as fp:
        S = [str.strip() for str in fp.readlines()]
    nodes = []
    for str in S:
        nodes.append(str)
    length = len(nodes[0])
    edges = [get_edge(n, len(S[0])) for n in nodes]
    out = nodes[0]
    del edges[0]
    while len(edges)>0:
        for index,item in enumerate(edges):
            if item[0] == out[1-length:]:
                out = out + item[1][-1:]
                del edges[index]
    print(out[:len(S)])


getAssembly()
