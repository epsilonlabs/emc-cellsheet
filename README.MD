# EMC Driver - Cellsheet
[![CircleCI](https://img.shields.io/circleci/project/github/epsilonlabs/emc-cellsheet/master.svg)](https://circleci.com/gh/epsilonlabs/emc-cellsheet/tree/master)
[![Coverage Status](https://img.shields.io/coveralls/github/epsilonlabs/emc-cellsheet/master.svg)](https://coveralls.io/github/epsilonlabs/emc-cellsheet?branch=master)
[![Commits to be Deployed](https://img.shields.io/github/commits-since/epsilonlabs/emc-cellsheet/develop.svg?label=commits%20to%20be%20deployed)](https://github.com/epsilonlabs/emc-cellsheet/compare/develop...master)

**NOTE THIS DRIVER IS UNDER ACTIVE DEVELOPMENT. API MAY CHANGE WITHOUT WARNING**

Epsilon Model Connectivity (EMC) driver that supports low-level interaction with Spreadsheets files as models. This is an alternative to the [Spreadsheet Driver (_org.eclipse.epsilon.emc.spreadsheets_)](http://download.eclipse.org/epsilon/javadoc/org/eclipse/epsilon/emc/spreadsheets/package-summary.html) currently available in the main Epsilon distribution. Unlike the Spreadsheets Driver, Cellsheet uses a like-for-like metaphor to model a spreadsheet. Sheets are represented as Sheet types, Cells as Cell types, etc.

## Model Element Types
Cellsheet features model elements that map to the typical spreadsheet metaphor. Primary model elements available include:
- Book
  - Sheet
    - Row
      - Cell
        - _CellValue (common element)_
        - BooleanValue
        - StringValue
        - NumericValue
        - FormulaValue
          - FormulaTree
            - FormulaToken

## Excel Driver Capabilities
Currently the implmenetation of the Driver supports Excel backed spreadsheets only i.e. *.xlsx files.

### Implmented:
- Reading of models
  - All elements are adapted to the model elements described in [Model Element Types](#model-element-types)
- Type based methods
  - ofType()
  - allOfType()
  - hasTypename()
  - hasType()
- owns()

### Todo:
- Kind based methods
- Model Element IDs
- Abstract Interpretation of Formula