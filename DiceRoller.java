import java.util.Random;

public class DiceRoller
{
	public static void main(String[] args)
	{
		int numRolls = 10;
		
		Random rand = new Random();
		HashMap<String, Integer> abilitySetToNumber = new HashMap<String, Integer>();
		
		for (int i=0; i<numRolls; i++)
		{
			AbilitySet abilitySet = AbilitySet.rollAbilities(rand);
			String abilities = abilitySet.abilities;
			Integer number = abilitySetToNumber.get(abilities);
			if (number == null)
			{
				number = Integer.valueOf(0);
			}
			
		}
	}
}