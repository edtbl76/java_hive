

/**
 * @author Patrick Ripley
 * @version 1.0
 * @created 13-Oct-2021 11:10:23 PM
 */
public class TestSelectionSort {

	private static final int a[] = {5, 3, 2, 1, 4};
	private static final int N = 5;

	public TestSelectionSort(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		TestSelectionSort my = new TestSelectionSort();
		System.out.println("Selection Sort Test Result:");
		System.out.println("------------------------------");
		System.out.println("Original Array:" + java.util.Arrays.toString(a));
		my.SelectionSort();
		System.out.println("Sorted   Array:" + java.util.Arrays.toString(a));
	}
	/* Begin - EA generated code for  Activities and Interactions */
	public void SelectionSort()
	{
		// behavior is a Activity
		int i = 0, j = 0, min = -1, temp = -1;;
		while (i < N - 1)
		{
			min = i;
			j = i+1;;
			while (j < N)
			{
				if (a[j] < a[min])
				{
					min = j;;
				}
				else
				{

				}
				j++;;
			}
			temp = a[min];
			a[min] = a[i];
			a[i] = temp;;
			i++;;
		}
	}

	/* End - EA generated code for  Activities and Interactions */
}//end TestSelectionSort