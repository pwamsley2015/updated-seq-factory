

/**
 * Contains an array of sequenced items, which are all run in parallel. <p>
 * 
 * WARNING: All items are run until the longest one finishes.
 * @author W.A.R.Lords
 */
public class SequencedMultipleItemAllRunUntilLastFinished implements SequencedItem {
    private final SequencedItem[] items;

//    public SequencedMultipleItem(SequencedItem[] items) {
//        this.items = items;
//    }
    public SequencedMultipleItemAllRunUntilLastFinished(SequencedItem... items) {
    	this.items = items; 
    }

    public void run() {
        for (int i = 0; i < items.length; i++) 
            items[i].run(); 
    }

    public double duration() {
        double max = 0;
        for (int i = 0; i < items.length; i++) 
            if (items[i].duration() > max)
                max = items[i].duration();
   
        return max;
    }

	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
	}
}