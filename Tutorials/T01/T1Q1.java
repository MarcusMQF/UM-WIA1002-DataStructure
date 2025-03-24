class Telephone {
    private String areaCode;
    private int number;
    private static int numberOfTelephoneObject;

    public Telephone(String areaCode, int number) {
        this.areaCode = areaCode;
        this.number = number;
    }

    //Accessor methods
    public String getAreaCode() {
        return this.areaCode;
    }

    public int getNumber() {
        return this.number;
    }

    public int getNumberOfTelephoneObject() {
        return numberOfTelephoneObject;
    }

    //Mutator methods
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    // A method that concatenates areCode and number with a dash in between
    public String makeFullNumber() {
        return areaCode + "-" + number;
    }

    public static void main(String[] args) {
        Telephone[] telephones = new Telephone[5];

        for(int i = 0; i < telephones.length; i++) {
            telephones[i] = new Telephone("03" , 79676300 + i);
            numberOfTelephoneObject++;
        }

        for(Telephone telephone : telephones) {
            System.out.println(telephone.makeFullNumber());
        }

        System.out.println("Number of Telephone objects created: " + telephones[0].getNumberOfTelephoneObject());
    }
}