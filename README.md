# InstantOnStartupGuide

Setup scripts to get started with InstantOn


# Quick Start

Create checkpoint image:
```
bash ./Scripts/privileged/build.sh
```

Run restore container:
```
bash ./Scripts/privileged/restoreContainer.sh
```

# Notes

The deployment yaml in YAMLs/common/my-app-criu.yaml needs to be
updated to have the restore image name.
