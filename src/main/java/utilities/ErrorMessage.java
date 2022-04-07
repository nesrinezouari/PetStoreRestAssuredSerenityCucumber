package utilities;

public enum ErrorMessage {
   
    Pet_200_Msg("successful operation"),
    Pet_400_Msg("Invalid ID supplied"),
    Pet_404_Msg("Pet not found"),
	Pet_405_Msg("Invalid input"),
	Pet_500_Msg("something bad happened");
    private String message;

    public String getMessage() {
        return this.message;
    }

    ErrorMessage(String message) {
        this.message = message;
    }
}
