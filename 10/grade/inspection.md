Pair: stoic-armadillos \
Commit: [ad45abab09d232f371d1251773cec11ea2ff3a72](https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/tree/ad45abab09d232f371d1251773cec11ea2ff3a72) \
Self-eval: https://github.khoury.northeastern.edu/CS4500-F23/stoic-armadillos/blob/73f9f24f27df0fdb61f18af2c1e4dcf95dd539e3/10/self-10.md \
Score: 60/110 \
Grader: Jessica Su

# 60/110 Program Inspection:
-10 missing a language-internal constructor for all configurations. We should translate the configuration from JSON to a data defintion in our language for flexibility. What if we wanted to communicate over XML instead of JSON?\
-10 missing functionality to create configuration data representation from JSON\
-10 does not enforce that each configuration specifies a fixed set of properties \
-10 unable to retrieve and modify configuration values \
-10 No unit tests for translating JSON into configuration objects in native language

