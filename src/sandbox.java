
public class sandbox {

	public static void main(String[] args) {
		String paragraph = "Judge Gladys Kessler said ruling that she faced “an anguishing Hobson’s choice” involving the detainee, Jihad Ahmed Mujstafa Diyab: to keep the order in place as the legal fight continues and risk that he dies, or to lift it and allow the military to take steps to keep him alive using procedures that inflict “unnecessary” suffering “The court simply cannot let Mr. Dhiab die,” she wrote, using an alternate spelling for the detainee’s name.The force-feeding procedure involves strapping a detainee into a restraint chair and inserting a tube through his nose and down his throat. Liquid nutritional supplement is then poure";
		String[] stringArray = paragraph.split(" ");
		for (int i=0; i<stringArray.length; i++)
		{
			System.out.println(stringArray[i]);
		}

	}

}
