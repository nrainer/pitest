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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class MutationStatusTestPair implements Serializable {

  private static final long serialVersionUID = 1L;

  private final int             numberOfTestsRun;
  private final DetectionStatus status;
  private final List<String>    assertionKillingTests;
  private final List<String>    exceptionKillingTests;
  private final List<String>    succeedingTests;

  public MutationStatusTestPair(final int numberOfTestsRun,
      final DetectionStatus status) {
    this(numberOfTestsRun, status, null);
  }

  public MutationStatusTestPair(final int numberOfTestsRun,
      final DetectionStatus status, final String killingTest) {
    this(numberOfTestsRun, status, killingTestToList(killingTest),
      Collections.emptyList(), Collections.emptyList());
  }

  public MutationStatusTestPair(final int numberOfTestsRun,
      final DetectionStatus status, final List<String> assertionKillingTests,
      final List<String> exceptionKillingTests,
      final List<String> succeedingTests) {
    this.status = status;
    this.assertionKillingTests = assertionKillingTests;
    this.exceptionKillingTests = exceptionKillingTests;
    this.succeedingTests = succeedingTests;
    this.numberOfTestsRun = numberOfTestsRun;
  }
  
  private static List<String> killingTestToList(String killingTest) {
    if (killingTest == null) {
      return new ArrayList<>();
    }
    
    return Arrays.asList(killingTest);
  }

  public DetectionStatus getStatus() {
    return this.status;
  }

  /**
   * Get the killing test.
   * If the full mutation matrix is enabled, the first test will be returned.
   */
  public Optional<String> getAssertionKillingTest() {
    if (this.assertionKillingTests.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(this.assertionKillingTests.get(0));
  }

  /** Get all killing tests.
   *  If the full mutation matrix is not enabled, this will only be the first killing test. 
   */
  public List<String> getAssertionKillingTests() {
    return assertionKillingTests;
  }

  /**
   * Get the killing test.
   * If the full mutation matrix is enabled, the first test will be returned.
   */
  public Optional<String> getExceptionKillingTest() {
    if (this.exceptionKillingTests.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(this.exceptionKillingTests.get(0));
  }

  public Optional<String> getAnyKillingTest() {
    if (getAssertionKillingTest().isPresent()) {
      return getAssertionKillingTest();
    }
    return getExceptionKillingTest();
  }
  
  /** Get all killing tests.
   *  If the full mutation matrix is not enabled, this will only be the first killing test. 
   */
  public List<String> getExceptionKillingTests() {
    return exceptionKillingTests;
  }

  /** Get all succeeding tests.
   *  If the full mutation matrix is not enabled, this list will be empty. 
   */
  public List<String> getSucceedingTests() {
    return succeedingTests;
  }

  public int getNumberOfTestsRun() {
    return this.numberOfTestsRun;
  }

  @Override
  public String toString() {
    if (this.assertionKillingTests.isEmpty() && this.exceptionKillingTests.isEmpty()) {
      return this.status.name();
    } else {
      return this.status.name() + " by " + this.assertionKillingTests + " and " + this.exceptionKillingTests;
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
    result = (prime * result)
        + ((this.succeedingTests == null) ? 0 : this.succeedingTests.hashCode());
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
    if (!Objects.equals( this.assertionKillingTests,other.assertionKillingTests)) {
      return false;
    }
    if (!Objects.equals( this.exceptionKillingTests,other.exceptionKillingTests)) {
    	return false;
    }
    if (!Objects.equals(this.succeedingTests, other.succeedingTests)) {
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
