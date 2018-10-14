/*
 * Copyright 2011 Henry Coles
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package org.pitest.mutationtest;

import java.io.Serializable;

import java.util.Optional;

public final class MutationStatusTestPair implements Serializable {

  private static final long serialVersionUID = 1L;

  private final int             numberOfTestsRun;
  private final DetectionStatus status;
  private final String  assertionKillingTests;
  private final String  exceptionKillingTests;
  /** may be null*/
  private final String  succeedingTest;

  public MutationStatusTestPair(final int numberOfTestsRun,
      final DetectionStatus status) {
    this(numberOfTestsRun, status, null, null, null);
  }

  public MutationStatusTestPair(final int numberOfTestsRun,
      final DetectionStatus status, final String assertionKillingTests) {
    // constructor is not relevant for the mutation matrix
    this(numberOfTestsRun, status, assertionKillingTests, null, null);
  }

  public MutationStatusTestPair(final int numberOfTestsRun,
      final DetectionStatus status, final String assertionKillingTests, final String exceptionKillingTests, final String succeedingTest) {
    this.status = status;
    this.assertionKillingTests = assertionKillingTests;
    this.exceptionKillingTests = exceptionKillingTests;
    this.succeedingTest = succeedingTest;
    this.numberOfTestsRun = numberOfTestsRun;
  }

  public DetectionStatus getStatus() {
    return this.status;
  }

  public Optional<String> getAssertionKillingTests() {
    return Optional.ofNullable(this.assertionKillingTests);
  }
  
  public Optional<String> getExceptionKillingTests() {
    return Optional.ofNullable(this.exceptionKillingTests);
  }

  public Optional<String> getSucceedingTest() {
    return Optional.ofNullable(this.succeedingTest);
  }

  public int getNumberOfTestsRun() {
    return this.numberOfTestsRun;
  }

  @Override
  public String toString() {
    if (this.assertionKillingTests == null && exceptionKillingTests == null) {
      return this.status.name();
    } else {
      return this.status.name() + " by " + this.assertionKillingTests + " or " + this.exceptionKillingTests;
    }

  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result)
        + ((this.assertionKillingTests == null) ? 0 : this.assertionKillingTests.hashCode());
    result = (prime * result)
        + ((this.exceptionKillingTests == null) ? 0 : this.exceptionKillingTests.hashCode());
    result = (prime * result) + this.numberOfTestsRun;
    result = (prime * result)
        + ((this.status == null) ? 0 : this.status.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final MutationStatusTestPair other = (MutationStatusTestPair) obj;
    if (this.assertionKillingTests == null) {
      if (other.assertionKillingTests != null) {
        return false;
      }
    } else if (!this.assertionKillingTests.equals(other.assertionKillingTests)) {
      return false;
    }
    if (this.exceptionKillingTests == null) {
      if (other.exceptionKillingTests != null) {
        return false;
      }
    } else if (!this.exceptionKillingTests.equals(other.exceptionKillingTests)) {
      return false;
    }
    if (this.numberOfTestsRun != other.numberOfTestsRun) {
      return false;
    }
    if (this.status != other.status) {
      return false;
    }
    return true;
  }

}
