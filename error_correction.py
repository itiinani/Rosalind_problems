from Bio import SeqIO
from collections import Counter

def hamming_distance(str1, str2):
    distance = 0
    for i in range(len(str1)):
        if str1[i] != str2[i]:
            distance += 1
    return distance

if __name__ == "__main__":
    f1= open("../rosalind/input.txt", 'r')
    check = []
    input = []
    correctly_sequenced = []
    incorrectly_sequenced = []
    out = []
    for i in SeqIO.parse(f1, "fasta"):
        input.append(str(i.seq))
        check.append(str(i.seq))
        check.append(str(i.seq.reverse_complement()))
    # for i in SeqIO.parse(f1,"fasta"):
    #      input.append(str(i.seq))
    counts = Counter(check)
    for i in counts:
        if counts[i] >= 2:
            correctly_sequenced.append(i)
        elif i in input: #not the manually reverse complemented
            incorrectly_sequenced.append(i)
    for str1 in incorrectly_sequenced:
        for str2 in correctly_sequenced:
            if hamming_distance(str1, str2) == 1:
                out.append([str1, str2])
    for i in out:
        print(i[0]+"->"+i[1])
    f1.close()