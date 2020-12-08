package model;
public class Slayer extends GameObject{
	private static final int COST = 50; // Es estática ya que es común para todos los slayers
	private static final int HEALTH = 3;
	private static final int HARM = 1;
	private static final String GENERICLETTER = "S";
	private Game game;
	
	public Slayer(int r, int c, Game game) {
		super(r, c, HEALTH, GENERICLETTER);
		this.game = game;
	}
	public static int getCostSlayer() {
		return COST;
	}
	public void advance() {} // Los slayers no se mueven
	public void deleteObjects() {} // Los slayers no necesitan ser contabilizados
	public void attack() {
		if (!isDead())
			for (int i = getCol() + 1; i < game.Cols(); ++i) {
				IAttack other = game.getAttackableInPosition(getRow(), i);
				if (other != null && other.receiveSlayerAttack(HARM))
					break; // no seguimos buscando si algun objeto ha sido atacado con éxito
			}
	}
	public boolean receiveVampireAttack(int damage) {
		decreaseLife(damage);
		return true;
	} // Solo se implementa la función de recibir ataque de vampiro
	// porque los slayers no dañan a los slayers
	public boolean vampiresWins() {return false;}
	public boolean receiveDraculaAttack() {
		setDeadObject();
		return true;
	}
}
