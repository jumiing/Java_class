package etc;

import java.util.Scanner;

public interface Factory {
    public Manageable create(Scanner scan);
}
