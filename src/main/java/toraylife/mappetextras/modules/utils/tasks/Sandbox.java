package toraylife.mappetextras.modules.utils.tasks;

public class Sandbox {

	public static void main(String[] args) {
		TaskLoop.getInstance()
				.first(t -> {
					System.out.println("hello");
					return t.result(123);
				}).then(t -> {
					int prev = t.getResult();
					return t.result(null);
				});
	}

	private static Task<Object, Integer> delegateTask() {
		return TaskLoop.getInstance()
				.first(t -> {
					System.out.println("hello");
					return null;
				})
				.thenWaitMillis(1000)
				.then(t -> {
					return t.result(123);
				});
	}

}
