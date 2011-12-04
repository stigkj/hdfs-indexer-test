#!/bin/bash

export CONFIGURATION;

CONFIGURATION=scripts/conf/without.sh
scripts/runConfiguration.sh

CONFIGURATION=scripts/conf/secondary1-5.sh
scripts/runConfiguration.sh

CONFIGURATION=scripts/conf/primary1-5.sh
scripts/runConfiguration.sh