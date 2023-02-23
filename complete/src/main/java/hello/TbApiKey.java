package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // This tells Hibernate to make a table out of this class
public class TbApiKey {

	@Id
        private Integer seq;
        public Integer getSeq() {
                return seq;
        }
        public void setSeq(Integer seq) {
                this.seq = seq;
        }

	private String apiKey;
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
}

