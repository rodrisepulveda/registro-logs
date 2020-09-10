package awto.registrologs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import awto.registrologs.entity.AwlogHashtag;

public interface AwlogHashtagRepository extends JpaRepository<AwlogHashtag, Integer> {

	AwlogHashtag findByDescription(String hashTag);

}
