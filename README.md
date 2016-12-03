pStore

## Usage ##

VaultManager vm = new ParanoidVaultManager();
Vault vault = vm.initVault("id1", "password");
vault.addEntry(entry, entryDetail);
vault.getEntry("website");
vault.getEntryDetails("website");
vault.lock();
vault = vm.unlockVault("id1","password");
vault.getEntry("website");
vm.listVaults();

## Internals ##

When a vault is initialized, a new encrypted empty vault
is generated. The password is used to generated a fresh 'SecondaryKey'
using a fresh salt. This secondary key is what is used to encrypt the empty vault
which is stored using its identifier.
Vaults are active while they are unlocked. They are locked
either manually or by the key expiring. That have to be unlocked
again to be usable.

Note that the password is used to encrypt the secondary key,
which is what is used to encrypt the vault. When an entry is added
to the vault, the details are encrypted with a 'fresh' secondary
key associated only to the entry. This secondary key was encrypted
by the vault's secondary key. This means that the password was only
used to encrypt the base entry. The protection this provides is only
that individual entries are typically not decrypted at the same time. By
decrypting the vault you can decrypt each entry; this scheme does not change
this.

Each time an individual entry detail is saved a new key is generated. This
key is never used twice. There is no salt for this key as the key is truly
random set of 256 bits each time.

Each vault has their own secondary key, and each secondary key is decrypted
by the the user's password and salt associated with the vault. This process
is defined by the crypto project, which simply uses SCrypt to hash the password
in a manner that makes brute-force attacks time-consuming. Keys are 256 bits
long, and encryption is via AES with GCM mode, which is the default for the
crypto package in paranoid mode. The weakest link is still the password.