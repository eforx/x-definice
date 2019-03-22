/* NOTE: this source code was generated by org.xdef.GenXComponent.
 * DO NOT MAKE ANY MODIFICATION!
 */
package components;

import org.xdef.sys.SUtils;
import org.xdef.XDFactory;
import org.xdef.XDPool;
import java.io.ByteArrayInputStream;

/** This class contains encoded data generated from XDPool object.
 * See the static method getXDPool().
 */
public final class TownPool {

	/** Get XDPool from encoded data.
	 * @return XDPool object.
	 */
	public static final XDPool getXDPool() {
		if (xdp != null) return xdp;
		try {
			return xdp = XDFactory.readXDPool(new ByteArrayInputStream(
				SUtils.decodeBase64("H4sIAAAAAAAAAM1ZzW/jRBQf103SdNOl6lZb2KVqINEKIfBnYsc9dfxVKrVLRSpUJCRkErdr1DjFdvqxl+V/4B9AghsSNw6cOaD9ExBnzly5QJmxx46bps2W1u5ONXmZN5OZ937z5s1706N2eVcXGYHhGQGcvThDBX9QABUKfIM+cUWlUIK1j/oD357v9HuHfdd2A58JGTOwtm17ft/dTPVEnKrTOzywe5hTTXVqTuA8t1GvG9jentWxx3QWYW2nf+zWz/ecVu2TwHa7frVrBRazdaodWL5/T6t9YvtOF49aSP1gx3YtRBdT3Wz8jb0w7mph6eWxs6zBbtezfb9EaHX8KNPqOQenT62ePbNp+QH+sjJ+5LpzZLu4v2w6XjQSvI/wfxzuS0HT4Q5EzflwU6gyeJsKd2obf0TluiNptA23O+MtjqR2AJjFXxbTG844frvnBM9KhFbSndRi39tnTrr2HnPo9TvM7q6OetNrV2ZYNjJQ8DuY/9+iDcB8IewZ7tQSqESz3GC6xEJuMhvW8h7DJPY5VHTcpOh01/2g+/EgALPrEYcKOYbnRRw0kipizoYLypgxhRiz9Q192/J826uDx5hJI+YcYg5c5+uB3baDOuDXsU/BkhKKdQTTFEzWBik54vZne86Bvcrqq2znyGd141P2K+vIYvGmftjpezbrex22Zzku2+13BvjMWoHTRy3HD1j7xMIn2Q8HGVHji10NhjaBJqfg8rMgOFxl2ePjY8Y/RV7IYjrPw9lZkeGRnP9gKc5uUObBuDKq57kSbiyYgrWV2XgWQE+ddK8Wlj7pHdRTI45FBpk/u7u1yfKK0mJdZEj+IfKxBTTQ9ZcvDhU4jmPDTpYG9DT2upEokbxUtjAUCf2R0J8y3Hpyp1RVU9IMXmhBwTCanCSooqRCXmk2oWnypsiBWP8pCpSm8VG8EpDr2Mr1AfklQ0AqESDsWuhuKkU/8Bx3n0QAdLEdeLYdxLqDFzexhFfSvUTor4S+zFD3OaJ7pGRVknkR8pqoNDVN5g1e4SQJanoLmlAyTVNKbIJ+VZugs8PlzwxxeXAOl0tMoxBGfjlaxgyhfxH6d34IhKpWW1KT04yGKvICrzZ5udniWs2Gxjd0URJlVaTi+BiQKy4pI2Dl4FMrhCUTupohWLHaEwFKDlBhCpTop4PepPNTzA4QI1ufGgKCTs6gBygcXUealuIcITk2lyhezk7xdoaKzyeKxzHnhQslCrpzdBtzhLVPaC9D/e/H+pPct9lSRKMpy4KsiZLUNARO0s1WS1Y0qcVDTaCSxBkMPTy4gFIO/oKMoL4j9PsMUUqUnohP4jBKyGEMc6xJbqOSHS4/Z3vpRLiwa8N08twBAqUkM5yEwRuoLmSDwW8ZYrAwxCDJgc9DEIqg5p46qknqqL7OqaN6h6kjoLTct0VLtkV7nbdFu8NtQbduOXlmzPFKmSJ0i9DtDPc//fxb5XVZ1xo6hDKnwqbAKbIhKSKP8jiZh5I8zO1nkLz0TPzDS2MSxFq8XWhoQj8n1MoQmrFP31VRaPANWTIbkiQguHjTMHRZk9CfpigKBxOMyjS6eZMX6UvNB7Ufobp0exhNE/otoT9kiNGkh/jRW3h2+Jh/FSJVVJdvD5E4p3xJ6B8ZIjLxnxijkIxmNuPweIJq7fbwiJ/J/iVdcxnicfW/fsaEKKFEeu73oZ7ch/rrfB/qdxqmQCp84qbg8KWbUnH0MrwnKW3kzqT0C1CBsm5/OdjfcPf6RLv7I5aJg3CQXbRMwbOhuZ1bEwzTyRqqdE4ypNfEpZKSqZiTDOk1023sfMo5y/ABofFBeYj7cpIhvWa6/R7GJmcZuJG2gupCzjKohMaxVx3VxexuDEpLyRCv2SA0jm0WUF3KSYZ4zTdH2u+g+ihnGQRC44jmAarLOckQr/nWSPtdVKs5yxDjEPtubKu1nGSI13w40l5B9UnOMvBH7f8ANsm6T4YkAAA=")));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	private static XDPool xdp = null;
}