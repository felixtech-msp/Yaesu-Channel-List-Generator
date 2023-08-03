package at.oe5eir.yaesu.channels.config;

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

public final class Channel {
    /**
     * only one in the entire set is true, rest false
     */
    private final boolean priorityCh = false;
    /**
     * receive frequency (repeater transmit frequency)
     */
    private final double rxFrequency;
    /**
     * transmit frequency (repeater receive frequency)
     */
    private final double txFrequency;
    /**
     * repeater shift (always positive number)
     */
    private final double offsetFrequency;
    /**
     * direction of repeater shift
     */
    private final OffsetDirection offsetDirection;
    /**
     * automatic operating mode selection
     */
    private final boolean autoMode = false;
    /**
     * operating mode (FM/AM)
     */
    private final OperatingMode operatingMode = OperatingMode.FM;
    /**
     * operating mode (FM/AM or C4FM)
     */
    private final DigAnalog digAnalog;
    /**
     * all of them either true or false
     */
    private final boolean tag = false;
    /**
     * channel name
     */
    private final String name;
    /**
     * subaudiotone mode
     */
    private final ToneMode toneMode;
    /**
     * CTCSS frequency (in and out)
     */
    private final Double ctcssFrequency;
    /**
     * DCS Code
     */
    private final int dcsCode = 23;
    /**
     * DCS Polarity
     */
    private final DcsPolarity dcsPolarity = DcsPolarity.RNTN;
    /**
     * range 300 to 3000 in 100 steps
     */
    private final int userCtcss = 1600;
    /**
     * C4FM RX ID (range 0 to 99)
     */
    private final int rxDgId = 0;
    /**
     * C4FM TX ID (range 0 to 99)
     */
    private final int txDgId = 32;
    /**
     * transmit power
     */
    private final TxPower txPower = TxPower.High;
    /**
     * unknown
     */
    private final Skip skip = Skip.OFF;
    /**
     * automatic bandwidth selection
     */
    private final boolean autoStep = false;
    /**
     * bandwidth
     */
    private final double step = 12.5;
    /**
     * unknown
     */
    private final boolean memoryMask = false;
    /**
     * unknown
     */
    private final boolean att = false;
    /**
     * squelch level (OFF, Level 1 to Level 9)
     */
    private final String sMeterSql = "Level 1";
    /**
     * unknown
     */
    private final boolean bell = false;
    /**
     * unknown
     */
    private final boolean narrow = false;
    /**
     * unknown
     */
    private final boolean clockShift = false;
    private final boolean bank1 = false;
    private final boolean bank2 = false;
    private final boolean bank3 = false;
    private final boolean bank4 = false;
    private final boolean bank5 = false;
    private final boolean bank6 = false;
    private final boolean bank7 = false;
    private final boolean bank8 = false;
    private final boolean bank9 = false;
    private final boolean bank10 = false;
    private final boolean bank11 = false;
    private final boolean bank12 = false;
    private final boolean bank13 = false;
    private final boolean bank14 = false;
    private final boolean bank15 = false;
    private final boolean bank16 = false;
    private final boolean bank17 = false;
    private final boolean bank18 = false;
    private final boolean bank19 = false;
    private final boolean bank20 = false;
    private final boolean bank21 = false;
    private final boolean bank22 = false;
    private final boolean bank23 = false;
    private final boolean bank24 = false;
    /**
     * comment
     */
    private final String comment = "";

    /**
     * Constructor fot FM channels
     * @param rxFrequency receive frequency
     * @param txFrequency transmit frequency
     * @param offsetDirection repeater offset direction
     * @param name channel name
     * @param toneMode squelch mode
     * @param ctcssFrequency CTCSS frequency
     */
    public Channel(double rxFrequency, double txFrequency, OffsetDirection offsetDirection, String name, ToneMode toneMode, Double ctcssFrequency) {
        this.rxFrequency = rxFrequency;
        this.txFrequency = txFrequency;
        this.offsetFrequency = Math.abs(rxFrequency - txFrequency);
        this.offsetDirection = offsetDirection;
        this.name = name;
        this.toneMode = toneMode;
        this.ctcssFrequency = ctcssFrequency;
        this.digAnalog = DigAnalog.FM;
    }

    /**
     * Constructor fot C4FM channels
     * @param rxFrequency receive frequency
     * @param txFrequency transmit frequency
     * @param offsetDirection repeater offset direction
     * @param name channel name
     */
    public Channel(double rxFrequency, double txFrequency, OffsetDirection offsetDirection, String name) {
        this.rxFrequency = rxFrequency;
        this.txFrequency = txFrequency;
        this.offsetFrequency = Math.abs(rxFrequency - txFrequency);
        this.offsetDirection = offsetDirection;
        this.name = name;
        this.toneMode = ToneMode.OFF;
        this.ctcssFrequency = null;
        this.digAnalog = DigAnalog.DN;
    }

    public String getChannelName() {
        return name;
    }

    @Override
    public String toString() {
        double ctcss = 88.5;

        if (ctcssFrequency != null)
            ctcss = ctcssFrequency;

        return String.format("%s,%.5f,%.5f,%.5f,%s,%s,%s,%s,%s,%s,%s,%s Hz,%03d,%s,%s Hz,RX %02d,TX %02d,%s,%s,%s,%sKHz,%s,%s,%s,%s,%s,%s,",
                b(priorityCh),rxFrequency,txFrequency,offsetFrequency,offsetDirection,b(autoMode),operatingMode,digAnalog,b(tag),
                name,toneMode,ctcss,dcsCode,dcsPolarity,userCtcss,rxDgId,txDgId,txPower,skip,b(autoStep),step,b(memoryMask),
                b(att),sMeterSql,b(bell),b(narrow),b(clockShift)
                ) +
                String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,0",
                    b(bank1),b(bank2),b(bank3),b(bank4),b(bank5),b(bank6),b(bank7),b(bank8),b(bank9),b(bank10),b(bank11),b(bank12),
                    b(bank13),b(bank14),b(bank15),b(bank16),b(bank17),b(bank18),b(bank19),b(bank20),b(bank21),b(bank22),b(bank23),b(bank24),
                    comment);
    }

    private static String b(boolean b) {
        return b ? "ON" : "OFF";
    }
}
