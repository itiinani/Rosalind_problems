
def debrujin_graph():
    with open('input.txt') as fp:
        S = [str.strip() for str in fp.readlines()]
    nodes = set()
    for str in S:
        nodes.add(str)
        nodes.add(reverse_complement(str))
    output = [get_edge(n,len(S[0])) for n in nodes]
    print('\n'.join(output))

def get_edge(n,l):
    edge = '(' + n[0:l - 1] + ', ' + n[1:l] + ')'
    return edge

def reverse_complement(str):
    c_map = {'A': 'T', 'T': 'A', 'G': 'C', 'C': 'G'}
    r_complement = ""
    for char in reversed(str.strip()):
        r_complement =r_complement + c_map[char]
    return r_complement

debrujin_graph()