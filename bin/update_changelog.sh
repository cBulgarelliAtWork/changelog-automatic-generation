#!/bin/bash
JIRA_URL="https://overit-spa.atlassian.net/jira/software/c/projects/GC/boards/450?modal=detail\&selectedIssue="
SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
COMMAND="git cliff -w $SCRIPT_DIR/.."
clear

echo "
3 2 1 <tag> 1 => CHANGELOG.md NOT UPDATED WITH COMPLETE COMMITS LIST
3 2 1 <tag> 2 => CHANGELOG.md UPDATED WITH COMPLETE COMMITS LIST
2 2 1 <> 1    => current tag CHANGELOG.md NOT UPDATED
1 1 1 <> 1    => latest tag CHANGELOG.md NOT UPDATED (WITH UNRELEASED COMMITS LIST ONLY)

Choose which commits to be processed
1 - Processes the commits starting from the latest tag
2 - Processes the commits that belong to the current tag
3 - Processes all commits
"
read WHICH_COMMITS
case "$WHICH_COMMITS" in
  1)
    echo "the commits starting from the latest tag will be processed"
    COMMAND=$COMMAND" --latest"
    ;;
  2)
    echo "the commits that belong to the current tag will be processed"
    COMMAND=$COMMAND" --current"
    ;;
  3)
    echo "all commits will be processed"
    ;;
  *)
    echo "Unexpected value. Possible values are: 1, 2, 3"
    exit 1
esac

echo "
=========================================================
Choose if process the commits that do not belong to a tag
1 - Yes
2 - No
"
read UNRELEASED_COMMITS_ONLY
case "$UNRELEASED_COMMITS_ONLY" in
  1)
    echo "the commits that do not belong to a tag will be processed"
    COMMAND=$COMMAND" --unreleased"
    ;;
  2)
    echo "the commits that do not belong to a tag will NOT be processed"
    ;;
  *)
    echo "Unexpected value. Possible values are: 1, 2"
    exit 1
esac

echo "
=============================================
Choose which git-cliff configuration file use
1 - keepachangelog.toml
2 - cliff.toml
"
read CONFIG_FILE
case "$CONFIG_FILE" in
  1)
    echo "Chosen git-cliff configuration file: keepachangelog.toml"
    COMMAND=$COMMAND" -c keepachangelog.toml"
    ;;
  2)
    echo "Chosen git-cliff configuration file: cliff.toml"
    COMMAND=$COMMAND" -c cliff.toml"
    ;;
  *)
    echo "Unexpected value. Possible values are: 1, 2"
    exit 1
esac

echo "
===================================================
Choose if set the tag for the latest version
<nothing> - an unreleased version will be generated
<tag value> - the provided tag will be generated
"
read TAG
[ -n "$TAG" ] && {
  echo "the provided tag $TAG will be generated"
  COMMAND=$COMMAND" --tag $TAG"
} || echo "an unreleased version will be generated"


echo "
=============================================
Choose if update the CHANGELOG.md
1 - the CHANGELOG.md file will NOT be updated
2 - the CHANGELOG.md file will be updated
"
read UPDATE_FILE
case "$UPDATE_FILE" in
  1)
    echo "the CHANGELOG.md file will NOT be updated"
    ;;
  2)
    echo "the CHANGELOG.md file will be updated"
    COMMAND=$COMMAND" -o $SCRIPT_DIR/../CHANGELOG.md"
    ;;
  *)
    echo "Unexpected value. Possible values are: 1, 2"
    exit 1
esac

echo "
=================================
$COMMAND"
START_TIME=$(date +%s)
$COMMAND
END_TIME=$(date +%s)
echo "It took $(($END_TIME - $START_TIME)) seconds to generate the CHANGELOG.md..."
case "$UPDATE_FILE" in
  2)
    echo "the CHANGELOG.html file will be updated"
    echo $JIRA_URL
    # from GC-NNN to [GC-NNN](<JIRA link>)
    sed "s@\(GC-\)\([0-9]*\)@\[\\1\\2\]\($JIRA_URL\\1\\2\)@" $SCRIPT_DIR/../CHANGELOG.md >$SCRIPT_DIR/../CHANGELOG.tmp
    mv -f $SCRIPT_DIR/../CHANGELOG.tmp $SCRIPT_DIR/../CHANGELOG.md
    grip $SCRIPT_DIR/../CHANGELOG.md --export $SCRIPT_DIR/../CHANGELOG.html
#    sed "s/\(GC-\)\([0-9]*\)/\<a href=\"https:\/\/overit-spa.atlassian.net\/jira\/software\/c\/projects\/GC\/boards\/\\2\?modal=detail\&selectedIssue=\\1\\2\">\\1\\2\<\/a\>/" $SCRIPT_DIR/../CHANGELOG.html > $SCRIPT_DIR/../CHANGELOG.html
    ;;
  *)
    ;;
esac