package org.usfirst.frc.team2485.auto;

public class Tester {
	
	public static void main(String[] args) {
		
		Sequencer seq = createTester();  
		
		while (true) {
				
			if (seq != null && seq.run()) {
				seq = null;
				System.out.println("Finished! :D");
			}
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}	
	}
	
	private static Sequencer createTester() {
		return new Sequencer(
				new PrintItemBadName(.01), 
				new SequencedMultibleItem(
						new PrintItemBadName(1), 
						new PrintItemBadName(3)
					), 
				new PrintItemBadName(.1)
		); 
	}

}

class PrintItemBadName implements SequencedItem {

	private double timeout; 
	
	public PrintItemBadName(double timeout) {
		this.timeout = timeout; 
	}
	
	@Override
	public void run() {
		System.out.println("I'm supposed to run for " + (int)timeout + " seconds.");
	}

	@Override
	public double duration() {
		return timeout; 
	}
	
	@Override
	public void finish() {
		System.out.println("<<<< I ran for " + (int)timeout + " seconds. >>>>");
	}
	
}
