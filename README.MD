# EMC Driver - Cellsheet
[![CircleCI](https://circleci.com/gh/epsilonlabs/emc-cellsheet/tree/master.svg?style=shield)](https://circleci.com/gh/epsilonlabs/emc-cellsheet/tree/master)
[![Coverage Status](https://coveralls.io/repos/github/epsilonlabs/emc-cellsheet/badge.svg?branch=hotfix%2Fcircleci)](https://coveralls.io/github/epsilonlabs/emc-cellsheet?branch=hotfix%2Fcircleci)

Epsilon Model Connectivity (EMC) driver that supports low-level interaction with Spreadsheets files as models. This is an alternative to the [Spreadsheet Driver (_org.eclipse.epsilon.emc.spreadsheets_)](http://download.eclipse.org/epsilon/javadoc/org/eclipse/epsilon/emc/spreadsheets/package-summary.html) currently available in the main Epsilon distribution.

Unlike the Spreadsheets Driver, Cellsheet 

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

## Supported Technologies
- Microsoft Excel (pre 97 versions may work)

## Driver Capabilities
Below is a list of the capabilities of the driver along with their implkementation status. Items that are ~struck through~ are not currently implemented:
- Reading/Loading Models
- ~Storing Models~
- Type based methods including:
  - ofType()
  - allOfType()
  - typeOf()
  - hasTypename()
  - hasType()
  - ~Kind based methods~
- owns()
- ~Model Element IDs~
