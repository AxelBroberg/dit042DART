public class CustomerRegular extends Customer {

    CustomerRegular(String name){
        super(name);
    }


    @Override

    public boolean libraryFull(){
        return getLibrary().size() >= 1;
    }

}
