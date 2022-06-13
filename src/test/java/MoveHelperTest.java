import com.evansitzes.logic.MoveHelper;
import com.evansitzes.exception.TranslationException;
import com.evansitzes.model.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoveHelperTest {

    @Test
    void testValidCoordinateTranslation() throws TranslationException {
        final Point p1 = MoveHelper.translateCoordinates("A2");
        Assertions.assertEquals(0, p1.x);
        Assertions.assertEquals(1, p1.y);

        final Point p2 = MoveHelper.translateCoordinates("A10");
        Assertions.assertEquals(0, p2.x);
        Assertions.assertEquals(9, p2.y);

        final Point p3 = MoveHelper.translateCoordinates("D4");
        Assertions.assertEquals(3, p3.x);
        Assertions.assertEquals(3, p3.y);

        final Point p4 = MoveHelper.translateCoordinates("F1");
        Assertions.assertEquals(5, p4.x);
        Assertions.assertEquals(0, p4.y);

        final Point p5 = MoveHelper.translateCoordinates("J5");
        Assertions.assertEquals(9, p5.x);
        Assertions.assertEquals(4, p5.y);

        final Point p6 = MoveHelper.translateCoordinates("J10");
        Assertions.assertEquals(9, p6.x);
        Assertions.assertEquals(9, p6.y);
    }

    @Test
    void testInvalidCoordinateThrowsException() {
        assertThrows(TranslationException.class, () -> {
            MoveHelper.translateCoordinates("Z2");
        });

        assertThrows(TranslationException.class, () -> {
            MoveHelper.translateCoordinates("A11");
        });

        assertThrows(TranslationException.class, () -> {
            MoveHelper.translateCoordinates("A-1");
        });

        assertThrows(TranslationException.class, () -> {
            MoveHelper.translateCoordinates("");
        });

        assertThrows(TranslationException.class, () -> {
            MoveHelper.translateCoordinates("...");
        });

        assertThrows(TranslationException.class, () -> {
            MoveHelper.translateCoordinates("aaaaaaa");
        });

        assertThrows(TranslationException.class, () -> {
            MoveHelper.translateCoordinates(null);
        });
    }
}
