#!/bin/bash
set -e

# Create databases
psql -U postgres -c "CREATE DATABASE authdb;"
psql -U postgres -c "CREATE DATABASE authordb;"
psql -U postgres -c "CREATE DATABASE bookdb;"
