package info.izumin.java.hanoiddfs;

public class SearchHanoiIDDFS {

	public static void main(String[] args) {
		HanoiIDDFS iddfs = new HanoiIDDFS(3);
		for (int i = 1; ; i++) {
			System.out.println("// limit = " + String.valueOf(i) + " ****************");
			if (iddfs.search(i)) break;
		}
		System.out.println(iddfs.getResult());
	}

}
