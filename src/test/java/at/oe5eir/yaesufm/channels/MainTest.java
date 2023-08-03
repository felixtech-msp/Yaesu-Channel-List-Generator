package at.oe5eir.yaesufm.channels;

/*
 *  Copyright (C) 2023 OE5EIR @ https://www.oe5eir.at/
 *
 *  Licensed under the GNU General Public License v3.0 (the "License").
 *  You may not use this file except in compliance with the License.
 *
 *  You may obtain a copy of the License at
 *      https://www.gnu.org/licenses/gpl-3.0.en.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MainTest {
    @Test
    public void testEqualsEquals() {
        double a = 7.600000000000023;
        double b = 7.6;
        assertTrue(Main.equals(a,b));
    }

    @Test
    public void testEqualsBiggerThan() {
        double a = 7.600000000000023;
        double b = 7.5;
        assertFalse(Main.equals(a,b));
    }

    @Test
    public void testEqualsSmallerThan() {
        double a = 7.600000000000023;
        double b = 7.7;
        assertFalse(Main.equals(a,b));
    }

    @Test
    public void testCompareDoubleEquals() {
        double a = 88.5;
        double b = 88.5;
        assertTrue(Main.compareDouble(a,b));
    }

    @Test
    public void testCompareDoubleNotEquals() {
        double a = 88.5;
        double b = 162.2;
        assertFalse(Main.compareDouble(a,b));
    }
}