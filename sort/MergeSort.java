package sort;

public class MergeSort {
    static void merge(final int[] arr, int start, final int mid, final int end) {
        final int[] out = new int[end - start];
        int i = start, j = mid, k = 0;
        while (i < mid && j < end)
            out[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        while (i < mid)
            out[k++] = arr[i++];
        while (j < end)
            out[k++] = arr[j++];
        k = 0;
        while (start < end)
            arr[start++] = out[k++];
    }

    static void sort(final int[] arr, final int start, final int end) {
        if (start == end - 1)
            return;
        final int mid = (start + end) / 2;
        sort(arr, start, mid);
        sort(arr, mid, end);
        merge(arr, start, mid, end);
    }

    public static void sort(final int[] arr) {
        sort(arr, 0, arr.length);
    }
}