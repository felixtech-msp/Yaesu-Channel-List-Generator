# Repeater List Generator for Yaesu FT3D Radios

This application creates the repeater list based on the OEVSV database for Yaesu FT3D radios which can be imported to the radio via the CPS.
It generates a channel list with all Austrian FM and C4FM repeaters.

## Requirements

 - Java SE 8 Runtime Environment or newer
 - Yaesu FT3D Programmer ADMS-11 Version 1.0.0.0 or newer
 - IntelliJ IDEA (if you want to modify this program)

## Run the application with a user interface (GUI)

Double-click the downloaded release file. Java should automatically open the application.

## Run the application via Command Line (CLI)
Open a shell on your machine and navigate to the folder containing the downloaded or self-created JAR file.

You have to decide if you want the location name (mountain name) or the closest city as a channel name.

Mountain name as channel name: \
Run in a terminal: `java -jar yaesu-channel-list-generator.jar [OUTPUT FILE]` \
Example: `java -jar yaesu-channel-list-generator.jar channels.csv`

City name as channel name: \
Run in a terminal: `java -jar yaesu-channel-list-generator.jar [OUTPUT FILE] city` \
Example: `java -jar yaesu-channel-list-generator.jar channels.csv city`

## Import into CPS

 1. Read the radio to the microSD card (`DISP > SD CARD > 1 BACKUP > 1 Write to SD > OK`)
 2. Import codeplug (BACKUP.dat) from the SD card to CPS (`Communications > Get data from SD card...`).
 3. Import repeater list to codeplug (`File > Import`)
 4. Select desired Priority CH and Banks
 5. Export codeplug (BACKUP.dat) from the CPS to the SD card (`Communications > Send data to SD card...`).
 6. Write the radio (`DISP > SD CARD > 1 BACKUP > 2 Read from SD > OK`) and wait until the radio rebooted.

## Restrictions

The FT3D can't have different CTCSS settings for Rx and Tx. The Rx CTCSS setting of the repeater (Tx on the radio) is used for Rx and Tx.
If Rx and Tx CTCSS settings don't match, a warning is displayed.

## Support

If you need support, have questions or feature requests, contact me. You find my contact information on my website: https://www.oe5eir.at/

## License

Copyright (C) 2023 OE5EIR

Licensed under the GNU General Public License v3.0 (the "License"). \
You may not use this project except in compliance with the License.

You may obtain a copy of the License at: \
https://www.gnu.org/licenses/gpl-3.0.en.html

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
