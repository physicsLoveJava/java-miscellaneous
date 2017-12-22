
## String

```code

//indexOf
static int indexOf(char[] source, int sourceOffset, int sourceCount,
        char[] target, int targetOffset, int targetCount,
        int fromIndex) {
    if (fromIndex >= sourceCount) {
        return (targetCount == 0 ? sourceCount : -1);
    }
    if (fromIndex < 0) {
        fromIndex = 0;
    }
    if (targetCount == 0) {
        return fromIndex;
    }

    char first = target[targetOffset];
    int max = sourceOffset + (sourceCount - targetCount);

    for (int i = sourceOffset + fromIndex; i <= max; i++) {
        /* Look for first character. */
        if (source[i] != first) {
            while (++i <= max && source[i] != first);
        }

        /* Found first character, now look at the rest of v2 */
        if (i <= max) {
            int j = i + 1;
            int end = j + targetCount - 1;
            for (int k = targetOffset + 1; j < end && source[j]
                    == target[k]; j++, k++);

            if (j == end) {
                /* Found whole string. */
                return i - sourceOffset;
            }
        }
    }
    return -1;
}


//lastIndexOf
static int lastIndexOf(char[] source, int sourceOffset, int sourceCount,
        char[] target, int targetOffset, int targetCount,
        int fromIndex) {
    /*
     * Check arguments; return immediately where possible. For
     * consistency, don't check for null str.
     */
    int rightIndex = sourceCount - targetCount;
    if (fromIndex < 0) {
        return -1;
    }
    if (fromIndex > rightIndex) {
        fromIndex = rightIndex;
    }
    /* Empty string always matches. */
    if (targetCount == 0) {
        return fromIndex;
    }

    int strLastIndex = targetOffset + targetCount - 1;
    char strLastChar = target[strLastIndex];
    int min = sourceOffset + targetCount - 1;
    int i = min + fromIndex;

    startSearchForLastChar:
    while (true) {
        while (i >= min && source[i] != strLastChar) {
            i--;
        }
        if (i < min) {
            return -1;
        }
        int j = i - 1;
        int start = j - (targetCount - 1);
        int k = strLastIndex - 1;

        while (j > start) {
            if (source[j--] != target[k--]) {
                i--;
                continue startSearchForLastChar;
            }
        }
        return start - sourceOffset + 1;
    }
}

//regionMatches
public boolean regionMatches(boolean ignoreCase, int toffset,
        String other, int ooffset, int len) {
    char ta[] = value;
    int to = toffset;
    char pa[] = other.value;
    int po = ooffset;
    // Note: toffset, ooffset, or len might be near -1>>>1.
    if ((ooffset < 0) || (toffset < 0)
            || (toffset > (long)value.length - len)
            || (ooffset > (long)other.value.length - len)) {
        return false;
    }
    while (len-- > 0) {
        char c1 = ta[to++];
        char c2 = pa[po++];
        if (c1 == c2) {
            continue;
        }
        if (ignoreCase) {
            // If characters don't match but case may be ignored,
            // try converting both characters to uppercase.
            // If the results match, then the comparison scan should
            // continue.
            char u1 = Character.toUpperCase(c1);
            char u2 = Character.toUpperCase(c2);
            if (u1 == u2) {
                continue;
            }
            // Unfortunately, conversion to uppercase does not work properly
            // for the Georgian alphabet, which has strange rules about case
            // conversion.  So we need to make one last check before
            // exiting.
            if (Character.toLowerCase(u1) == Character.toLowerCase(u2)) {
                continue;
            }
        }
        return false;
    }
    return true;
}

//toString
public boolean equals(Object anObject) {
    if (this == anObject) {
        return true;
    }
    if (anObject instanceof String) {
        String anotherString = (String) anObject;
        int n = value.length;
        if (n == anotherString.value.length) {
            char v1[] = value;
            char v2[] = anotherString.value;
            int i = 0;
            while (n-- != 0) {
                if (v1[i] != v2[i])
                        return false;
                i++;
            }
            return true;
        }
    }
    return false;
}

```