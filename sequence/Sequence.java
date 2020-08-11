package sequence;

import java.util.LinkedList;

public class Sequence {
    private String s;

    public Sequence(final String s) {
        this.s = s;
    }

    public LinkedList<String> permutation() {
        final var list = new LinkedList<String>();
        if (s.length() < 2) {
            list.add(s);
            return list;
        }
        for (int i = 0; i < s.length(); i++) {
            final char ch = s.charAt(i);
            final String rem = s.substring(0, i) + s.substring(i + 1);
            final var sq = new Sequence(rem);
            final var sublist = sq.permutation();
            for (final var perm : sublist)
                list.add(ch + perm);
        }
        return list;
    }

    public LinkedList<String> subsequence() {
        final var list = new LinkedList<String>();
        if (s.equals("")) {
            list.add("");
            return list;
        }
        final char ch = s.charAt(0);
        s = s.substring(1);
        final var sq = new Sequence(s);
        final var sublist = sq.subsequence();
        for (final var substring : sublist)
            list.add(ch + substring);
        for (final var substring : sublist)
            list.add(substring);
        return list;
    }

    public LinkedList<String> substring() {
        final var list = new LinkedList<String>();
        for (int i = 0; i < s.length(); i++)
            for (int j = i; j < s.length(); j++)
                list.add(s.substring(i, j + 1));
        return list;
    }
}