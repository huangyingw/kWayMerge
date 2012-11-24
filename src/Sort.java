public class Sort {

	public Sort() {
	}

	public static void kwayMergesort(int[] data, int k) {
		kwayMergesortRecursive(data, 0, data.length - 1, k);
	}

	public static void kwayMergesortRecursive(int[] data, int low, int high,
			int k) {
		if (low < high) {
			for (int i = 0; i < k; i++) {
				kwayMergesortRecursive(data, low + i * (high - low + 1) / k,
						low + (i + 1) * (high - low + 1) / k - 1, k);
			}
			merge(data, low, high, k);
		}
	}

	public static void merge(int[] data, int low, int high, int k) {

		if (high < low + k) {
			Comparable[] subarray = new MergesortHeapNode[high - low + 1];
			for (int i = 0, j = low; i < subarray.length; i++, j++) {
				subarray[i] = new MergesortHeapNode(data[j], 0);
			}
			BinaryHeap heap = BinaryHeap.buildHeap(subarray);
			for (int j = low; j <= high; j++) {
				try {
					data[j] = ((MergesortHeapNode) heap.deleteMin()).getKey();
				} catch (EmptyHeapException e) {
					System.out.println("Tried to delete from an empty heap.");
				}
			}

		} else {
			// divide the array into k subarrays and do a k-way merge
			final int subarrSize = high - low + 1;
			final int[] tempArray = new int[subarrSize];

			// Make temp array
			for (int i = low; i < high + 1; i++)
				tempArray[i - low] = data[i];

			// Keep subarray index to keep track of where we are in each
			// subarray
			final int[] subarrayIndex = new int[k];
			for (int i = 0; i < k; i++)
				subarrayIndex[i] = i * (subarrSize) / k;

			// Build heap
			Comparable[] subarray = new MergesortHeapNode[k];
			for (int i = 0; i < k; i++)
				subarray[i] = new MergesortHeapNode(
						tempArray[subarrayIndex[i]++], i);

			BinaryHeap heap = BinaryHeap.buildHeap(subarray);

			// For each element low to high, find the lowest in each k subarray
			for (int i = low; i < high + 1; i++) {

				// Take lowest element and add back in to original array
				try {
					MergesortHeapNode a = ((MergesortHeapNode) heap.deleteMin());
					data[i] = a.getKey();
					if (subarrayIndex[a.getWhichSubarray()] < (a
							.getWhichSubarray() + 1) * (subarrSize) / k) {
						heap.insert(new MergesortHeapNode(
								tempArray[subarrayIndex[a.getWhichSubarray()]]++,
								a.getWhichSubarray()));

						// Increment the subarray index where the lowest element
						// resides
						subarrayIndex[a.getWhichSubarray()]++;
					}
				} catch (EmptyHeapException e) {
					System.out.println("Tried to delete from an empty heap.");
				}
			}
		}
	}

	public static int[] getRandomPermutationOfIntegers(int size) {
		int[] data = new int[size];
		for (int i = 0; i < size; i++) {
			data[i] = i;
		}
		// shuffle the array
		for (int i = 0; i < size; i++) {
			int temp;
			int swap = i + (int) ((size - i) * Math.random());
			temp = data[i];
			data[i] = data[swap];
			data[swap] = temp;
		}
		return data;
	}

	private static void testCorrectness() {
		int[] data = getRandomPermutationOfIntegers(100);

		for (int i = 0; i < data.length; i++) {
			System.out.println("data[" + i + "] = " + data[i]);
		}

		int k = 4;
		kwayMergesort(data, k);

		for (int i = 0; i < data.length; i++) {
			if (data[i] != i) {
				System.out
						.println("Error!  data[" + i + "] = " + data[i] + ".");
			}
		}
	}

	public static void main(String[] argv) {
		testCorrectness();
	}
}
