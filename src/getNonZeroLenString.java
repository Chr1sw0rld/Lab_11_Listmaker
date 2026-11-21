
public static String getNonZeroLenString(Scanner pipe, String prompt) {
    String retString = "";

    do {
        System.out.print("\n" + prompt + ": ");
        retString = pipe.nextLine();

        if (retString.trim().isEmpty()) {
            System.out.println("You must enter some text!");
        }

    } while (retString.trim().isEmpty());

    return retString;
}

void main() {
}
