import java.util.ArrayList;
import java.util.List;

//merge sorts for product sorting variants, descending, ascending, etc.

public class productSorting {
    void mergeProductLists(List<Product> list, int b, int m, int e)
    {
        List<Product> temp = new ArrayList<>(list.subList(b, e + 1));

        int p1 = b;
        int p2 = m+1;
        int p3 = b;
        while (p1 <= m && p2 <= e) {
            Product left = temp.get(p1 - b);
            Product right = temp.get(p2 - b);

            if (left.getDiscountPercentage() > right.getDiscountPercentage()) {
                list.set(p3++, left);
                p1++;
            } else {
                list.set(p3++, right);
                p2++;
            }
        }
        while (p1 <= m) {
            list.set(p3++, temp.get(p1 - b));
            p1++;
        }
    }



    public void mergeSort(List<Product> list)
    {
        mergeSort(list, 0, list.size() - 1);
    }
    void mergeSort(List<Product> list, int begin, int end)
    {
        if (begin < end)
        {
            int mid = (begin + end) / 2;
            mergeSort(list, begin, mid);
            mergeSort(list, mid + 1, end);
            mergeProductLists(list, begin, mid, end);
        }
    }
}
