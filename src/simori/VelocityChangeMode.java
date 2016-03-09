package simori;

/**
 * this is a class that is used under Performance mode when the L2 button is
 * clicked. here, when the grid button is pressed, the specific column and row
 * will be selected based on the button clicked. this will choose a node
 * velocity which can be between 0-127 and appears on the LCD.
 * 
 * @author Team G
 * 
 */
public class VelocityChangeMode implements Mode {
	private int velocity;

	/**
	 * constructor that creates the clockhand in a non runnable state.
	 */
	public VelocityChangeMode() {
		if (SimoriOn.getClockHand() != null) {
			SimoriOn.getClockHand().running.set(false);
		}
	}

	/**
	 * method that checks firstly that the button has been pressed and the
	 * coordinates of the button that has been clicked. once it has, then based
	 * upon the click, the velocity will be chosen within the given range.
	 * 
	 * @param button
	 */
	@Override
	public void processMatrixButton(GridButton button) {
		// TODO Auto-generated method stub
		System.out.println("Matrix button processed in Velocity Change Mode");

		// Highlight row and column of the button
		button.getGUI().highlightColumnAndRow(button.getCoordsX(),
				button.getCoordsY());

		// Set the velocity to x*y based on the button pressed
		if (SimoriOn.getClockHand() != null) {

			if (button.getCoordsX() * button.getCoordsY() <= 127) {
				velocity = button.getCoordsX() * button.getCoordsY();

			} else {
				velocity = 127;
			}

			SimoriOn.getInstance().getGui()
					.writeToLCD("Velocity: " + Integer.toString(velocity));
			System.out.println("Set velocity to " + velocity);
		}

	}

	/**
	 * method that is used when the OK button is pressed. here the LCD is set
	 * back to NULL and the Simori ON goes back to the performance mode
	 */
	public void processOKButton() {
		SimoriOn.getInstance().getGui().LCD.setText(null);

		SimoriOn.getClockHand().setVelocity(velocity);
		SimoriOn.getInstance().setMode(new PerformanceMode());
		SimoriOn.getInstance().getGui().turnOffFunctionButtons();
	}

}
