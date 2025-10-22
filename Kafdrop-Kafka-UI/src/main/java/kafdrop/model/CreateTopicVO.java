package kafdrop.model;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.RequiredArgsConstructor;

//@Data
//@RequiredArgsConstructor
@Schema(description = "Create topic model")
public final class CreateTopicVO {
	@Parameter(description = "Topic name")
	String name;

	@Parameter(description = "Number of partitions")
	int partitionsNumber;

	@Parameter(description = "Replication factor")
	int replicationFactor;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPartitionsNumber() {
		return partitionsNumber;
	}

	public void setPartitionsNumber(int partitionsNumber) {
		this.partitionsNumber = partitionsNumber;
	}

	public int getReplicationFactor() {
		return replicationFactor;
	}

	public void setReplicationFactor(int replicationFactor) {
		this.replicationFactor = replicationFactor;
	}

}
