#!/bin/bash
[ $# -eq 0 ] && { echo "Example usage: $0 1.0.0"; exit 1; }
echo "Generating in memory CHANGELOG.md for unreleased commits that do not belong to a tag"
SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
echo "Tag: $1"
echo "git cliff -v -w $SCRIPT_DIR/.. --unreleased --tag $1 --latest -c $SCRIPT_DIR/../keepachangelog.toml"
echo
echo
git cliff -v -w $SCRIPT_DIR/.. --unreleased --tag $1 --latest -c $SCRIPT_DIR/../keepachangelog.toml