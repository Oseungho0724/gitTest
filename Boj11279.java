import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine());
		MaxHeapImpl mhi = new MaxHeapImpl(n);
		StringBuilder sb = new StringBuilder();
		while (n-- > 0) {
			int now = Integer.parseInt(in.readLine());
			if (now == 0) {
				if (mhi.endIdx == 0) {
					sb.append(0 + "\n");
				} else {
					sb.append(mhi.remove() + "\n");
				}
			} else {
				mhi.add(now);
			}
		}
		in.close();
		System.out.print(sb);
	}

}

/**
 * 
 * @author jaewan song
 * @category algorithm
 * @version 1.0 �ִ� ���� �迭 ����(�켱���� ť) 
 *
 */
class MaxHeapImpl {
	private int[] heap;
	int endIdx = 0; // 0��° �ε����� ������� �ʴ´�.

	/**
	 * constructor
	 * 
	 * @param n : ���� ũ�⸦ ���ڰ����� �޴´�.
	 */
	MaxHeapImpl(int n) {
		heap = new int[n + 1]; // 0��° �ε����� ������� �����Ƿ� n���� ����ϱ� ����
	}

	// add
	/**
	 * �� �߰�
	 * 
	 * @param val : �߰��Ϸ��� ���� ���ڰ����� ����
	 */
	public void add(int val) {
		heap[++endIdx] = val; // ���� ������ ��忡 ���� �߰��Ѵ�
		for (int i = endIdx; i > 1; i /= 2) {
			if (heap[i / 2] < heap[i]) { // �ִ밪�� ��Ʈ�� ������.
				exchange(i / 2, i, heap[i / 2], heap[i]);
			}
		}
	}

	/**
	 * �� ��ȯ method
	 * 
	 * @param to      : ������� Ȥ�� ��Ʈ �ε���
	 * @param from    : ������� �ε���
	 * @param toVal   : ������� Ȥ�� ��Ʈ
	 * @param fromVal : ���� ���
	 */
	private void exchange(int to, int from, int toVal, int fromVal) {
		heap[to] = fromVal;
		heap[from] = toVal;
	}

	// remove
	/**
	 * ��Ʈ ������ ������
	 * 
	 * @return ��Ʈ�� ������
	 */
	public int remove() {
		int del = heap[1];
		heap[1] = heap[endIdx];
		heap[endIdx] = 0; // ������ ��� ����
		int now = 1;
		while (now * 2 < endIdx) {
			int left = now * 2;
			int right = now * 2 + 1;
			if (heap[now] < heap[left] || heap[now] < heap[right]) {
				if (heap[left] >= heap[right]) {
					exchange(now, left, heap[now], heap[left]);
					now = left;
				} else {
					exchange(now, right, heap[now], heap[right]);
					now = right;
				}
			} else {
				break;
			}
		}
		endIdx--;
		return del;
	}
}