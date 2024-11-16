Changes made:
- Removed Trimmed.java and Padded.java. These were showing checkstyle STUB errors, but are
    not required for the project.
- Added eqv method for the NewBlock class.
- Fixed tests for NewBlock

These are things we believe we were incorrectly marked off for on our first attempt, and have not been changed:
- Doesn't compile. All calls to 'mvn compile -q' on our end complete with no errors.
- No new block type. We created a new block type called NewBlock that vertially composes
    alternating rows of two asciiblocks.
- Not enough tests in TestNewBlock. We have 6 tests here, may have read the grading wrong.