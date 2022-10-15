package parkingsystem;

/**
* Tests for ScanType.java
*
* @author  Erik Grafton
* @version 1.0
* @since   July 11, 2021
*/

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ScanTypeTests {
  /**
   * Test Types
   */
  @Test
  public void testScanType() {
    assertEquals("ENTRY", ScanType.ENTRY.toString());
    assertEquals("ENTRYEXIT", ScanType.ENTRYEXIT.toString());
  }
}
