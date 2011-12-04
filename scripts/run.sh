#!/bin/bash

export CONFIGURATION;

CONFIGURATION=scripts/conf/without.sh
scripts/run.sh

CONFIGURATION=scripts/conf/secondary1-5.sh
scripts/run.sh

CONFIGURATION=scripts/conf/primary1-5.sh
scripts/run.sh
