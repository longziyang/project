package note.thread;

public class ThreadImp implements Runnable {

	private String name = "物流部门";

	@Override
	public void run() {

		synchronized (this) {

			for (int i = 0; i < 100; i++) {

				System.out.println(Thread.currentThread().getName() + "  " + name);

				if (Thread.currentThread().getName().equals("生产部门")) {

					// System.out.println("商品进入生产部门");
					if (name.equals("物流部门")) {

						System.out.println("商品加工完毕");
						name = "生产部门";
					} else {
						notifyAll();
						try {
							wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else if (Thread.currentThread().getName().equals("销售部门")) {

					// System.out.println("商品进入销售部门");
					if (name.equals("生产部门")) {

						System.out.println("商品销售完毕");
						name = "销售部门";
					} else {
						notifyAll();
						try {
							wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				} else if (Thread.currentThread().getName().equals("物流部门")) {

					// System.out.println("商品进入物流部门");
					if (name.equals("销售部门")) {

						System.out.println("商品运输完成");
						name = "物流部门";
					} else {
						notifyAll();
						try {
							wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}

		}
	}

}
