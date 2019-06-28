# smallWorld  

Shrinking worlds for those with a need!
  
This is a program built to split up large worlds into smaller pieces for external download. Currently in development for the CapeCraft server for after the reset.<br/>
Currently being worked on by mov51, a very new java developer with poor math skills. The math and code execution has been cleaned up by ewanm89, you can thank him for it working out so cleanly and efficiently.
<br/><br/>
Standalone we plan to have it be a very simple program. Intended to be used by placing it within the world folder you want to copy and opening via command line with a set of arguments that tell it what to retrieve. Use cases would be geting quick copies of a section of a live world, dispersing a world download after the reset of a large world, backing up a small part of a world, and exporting sections of a world for use in anbother world.<br/>

<br/><br/>
# Planned features  

- radius around a player region export "PlayerUUID, radius"
- predefined region export "regionName"
- player data exporting "UUID, UUID, ..."
  - Would need UUIDs of all playeRs
  - adds player data files to file export
- world gen options when exporting
  - void
  - seed based world gen
  - random world gen (no seed)
