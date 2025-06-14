# Test Plan Report

## 1. QA Step Information

- **Step Name**: Check metrics  
- **Step Number**: 3  
- **Artefacts Directory with Test Reports**: `./qa-artefacts`  
- **Output Directory**: `.`  
- **Check Metrics**: Coverage  
- **Target Value of Metrics**: `0.7`

---

## 2. New Test Report

The test report provides detailed coverage metrics for the codebase. Below is a summary of the findings:

### Overall Metrics:
- **Total Instructions Missed**: 67  
- **Total Instructions**: 514  
- **Instruction Coverage**: **86%**  
- **Branches Missed**: 4  
- **Branch Coverage**: **80%**  
- **Complexity Missed**: 15  
- **Total Complexity**: 68  
- **Lines Missed**: 25  
- **Total Lines**: 138  
- **Methods Missed**: 12  
- **Total Methods**: 58  
- **Classes Missed**: 0  
- **Total Classes**: 10  

### Package-Level Metrics:
| Package Name                          | Instruction Coverage | Branch Coverage | Lines Missed | Total Lines | Methods Missed | Total Methods |
|---------------------------------------|----------------------|-----------------|--------------|-------------|----------------|---------------|
| `com.sampleservice.demo.dto.outbound` | 45%                  | 0%              | 15           | 24          | 6              | 10            |
| `com.sampleservice.demo.util`         | 88%                  | 77%             | 3            | 26          | 1              | 8             |
| `com.sampleservice.demo.dto.inbound`  | 78%                  | 0%              | 3            | 16          | 3              | 8             |
| `com.sampleservice.demo`              | 37%                  | 0%              | 2            | 3           | 1              | 2             |
| `com.sampleservice.demo.service`      | 95%                  | 0%              | 1            | 17          | 0              | 8             |
| `com.sampleservice.demo.model`        | 92%                  | 0%              | 1            | 16          | 1              | 11            |
| `com.sampleservice.demo.config`       | 100%                 | 0%              | 0            | 18          | 0              | 3             |
| `com.sampleservice.demo.controller`   | 100%                 | 0%              | 0            | 13          | 0              | 6             |
| `com.sampleservice.demo.validator`    | 100%                 | 100%            | 0            | 5           | 0              | 2             |

---

## 3. Known Issues

No known issues were reported during this step.

---

## 4. Current Metric Value

The current metric value for **instruction coverage** is **0.86**, which exceeds the target value of **0.7**.

---

## 5. LLM Part

- **LLM Model Name**: `qwen-max`  
- **Cost of Report (Token Count)**: **9616 tokens**

---

## 6. Error of Step

No errors were encountered during this step.

--- 

This concludes the test plan report for the "Check metrics" step. The overall coverage metrics indicate strong performance, with the instruction coverage exceeding the target threshold. Further improvements can focus on addressing gaps in specific packages like `com.sampleservice.demo.dto.outbound` and `com.sampleservice.demo`.