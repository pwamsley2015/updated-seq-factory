
public class SequencedMultibleItemEachStopWhenFinished implements SequencedItem {

	private SequencedItem[] items; 
	private long startTime;

	private boolean firstRun; 

	public SequencedMultibleItemEachStopWhenFinished(SequencedItem... items) {
		this.items 		= items; 
		this.firstRun	= true; 
	}

	@Override
	public void run() {

		if (firstRun) {
			startTime = System.currentTimeMillis();
			firstRun = false; 
		}

		for (int currItemIndex = 0; currItemIndex < items.length; currItemIndex++) { 	
			
			SequencedItem curr = items[currItemIndex]; 

			if (curr == null)
				continue; 

			//need to garentee the last item runs its finish method before the multi item times out
			if (currItemIndex == items.length - 1 && 
					System.currentTimeMillis() - startTime + 20 >= curr.duration() * 1000) { //one iritation before the multi item stops
				curr.finish();
				return; 
			}
			else if (System.currentTimeMillis() - startTime <= curr.duration() * 1000)
				curr.run(); 
			else {
				curr.finish(); 
				items[currItemIndex] = null;
			}
		}
	}

	@Override
	public double duration() {

		double max = Double.MIN_VALUE; 

		for (int i = 0; i < items.length; i++) {
			if (items[i] == null)
				continue; 
			if (max < items[i].duration())
				max = items[i].duration(); 
		}

		return max; 
	}

	@Override
	public void finish() {}
}
